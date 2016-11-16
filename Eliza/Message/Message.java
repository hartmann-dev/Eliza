package Eliza.Message;

import java.util.Arrays;
import java.util.Objects;

public class Message implements IMessage {

    private final String message;
    private final String[] words;
    
    
    public Message(String msg) {
        message = msg;
        words  = cleanMessage(message).toLowerCase().split("\\s+");
    }
    
    public String getMessage(){
        return message;
    }
    
    @Override
    public String[] getWords() {
        return words;
    }

    @Override
    public String toString() {
        return message;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.message);
        return hash;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (getClass() != other.getClass()) {
            return false;
        }
        final Message otherMessage = (Message) other;
        return Objects.equals(this.message, otherMessage.message);
    }
    
    private String cleanMessage(String message){
        return message.replaceAll(("[^A-Za-z0-9\\sÄÖÜäöüß]"), "");
    }
    
    public boolean wordExists(String word){
        return Arrays.asList(words).contains(word);
    }

    
    
}
