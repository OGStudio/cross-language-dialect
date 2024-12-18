package ivac
import kotlin.js.JsExport

@JsExport
interface ctx_Context {
    /**
     * Имя только что изменённого поля
     *
     * Даёт возможность шудам/обработчикам/функциям поведения
     * реагировать на интересующие эти функции изменения и не
     * производить вычисления при каждом изменении контекста
     */
    var recentField: String

    /**
     * Получаем значение поля по его имени
     */
    fun field(name: String): Any
    /**
     * Создаём копию наследованного контекста
     *
     * В частности, это использует в своей работе `ctx_Controller` для управления очередью состояний, в которой он оперирует родительским типом `ctx_Context`
     */
    fun selfCopy(): ctx_Context
    /**
     * Задаём значение поля по его имени
     */
    fun setField(name: String, value: Any)
}

@JsExport
class ctx_Controller(
  var context: ctx_Context
) {
  internal var callbacks = mutableListOf<(c: ctx_Context) -> Unit>()
  internal var functions = mutableListOf<(c: ctx_Context) -> ctx_Context>()
  var isProcessingQueue = false
  internal var queue = mutableListOf<ctx_Context>()

  fun executeFunctions() {
    val c = queue.removeFirst()
    context.recentField = c.recentField
    context.setField(c.recentField, c.field(c.recentField))

    for (func in functions) {
      val ctx = func(context.selfCopy())
      if (ctx.recentField != "none") {
        queue.add(ctx)
      }
    }

    reportContext()
  }

  fun processQueue() {
    // Исключаем рекурсию.
    if (isProcessingQueue) {
      return
    }

    isProcessingQueue = true

    while (queue.size > 0) {
      executeFunctions()
    }

    isProcessingQueue = false
  }

  fun registerCallback(cb: (c: ctx_Context) -> Unit) {
    callbacks.add(cb)
  }

  fun registerFieldCallback(
    fieldName: String,
    cb: (ctx_Context) -> Unit
  ) {
    callbacks.add({c: ctx_Context -> if (c.recentField == fieldName) cb(c) })
  }

  fun registerFunction(f: (ctx_Context) -> ctx_Context) {
    functions.add(f)
  }

  fun reportContext() {
    for (cb in callbacks) {
      cb(context)
    }
  }

  fun set(fieldName: String, value: Any) {
    var c = context.selfCopy()
    c.setField(fieldName, value)
    c.recentField = fieldName
    queue.add(c)
    processQueue()
  }
}
