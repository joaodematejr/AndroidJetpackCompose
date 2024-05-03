fun main() {
   /** var - mutable ... */

    val button = Button(label = "Click me")
    button.onClick(message = "Click me")

    val superMario = Character(label = "Super Mario")
    superMario.onClick(message = "Super Mario")
   
}

class Button(val label: String): ClickEvent {
    override fun click(message: String) {
        println("Button clicked with message: $message")
    }
    
}

class Character(val name: String): ClickEvent {
    override fun click(name: String) {
        println("Character clicked with message: $message")
    }
    
}

//Interfaces
interface ClickEvent {
    fun onClick(message: String)
}