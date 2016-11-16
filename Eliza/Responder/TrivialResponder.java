package Eliza.Responder;

import Eliza.Message.IMessage;
import Eliza.Message.Message;
import Eliza.Message.RatedMessage;
import java.util.ArrayList;

public class TrivialResponder extends Responder {

    private static final double BASE_QUALITY = 0.4;
    
    private final ArrayList<Message> messages = new ArrayList<>();
    
    public TrivialResponder(String[] msgs){
        for(String msg : msgs){
            messages.add(new Message(msg));
        }
    }
    
    @Override
    public RatedMessage getAnswer(IMessage msg) {
        ArrayList<RatedMessage> allAnswers = calcAnswers(messages, BASE_QUALITY);
        return send(getTopAnswer(allAnswers));
    }
}
