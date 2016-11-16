package Eliza.Responder;

import Eliza.Message.IMessage;
import Eliza.Message.RatedMessage;

public interface IResponder {
    
    public RatedMessage getAnswer(IMessage msg);
    
}
