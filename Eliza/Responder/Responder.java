package Eliza.Responder;

import Eliza.Message.Message;
import Eliza.Message.RatedMessage;
import java.util.ArrayList;
import java.util.Random;

abstract class Responder implements IResponder {

    private static final double MALUS = 0.1;
    
    protected RatedMessage getTopAnswer(ArrayList<RatedMessage> answers) {
        double topQualtiy = 0.0;
        ArrayList<RatedMessage> topResponses = new ArrayList<>();

        for (RatedMessage response : answers) {
            double quality = response.getQuality();
            if (quality > topQualtiy) {
                topQualtiy = quality;
                topResponses.clear();
                topResponses.add(response);
            } else if (quality == topQualtiy) {
                topResponses.add(response);
            }
        }

        if (topResponses.size() >= 2) {
            return getRandomAnswer(topResponses);
        } else {
            return topResponses.get(0);
        }

    }

    private RatedMessage getRandomAnswer(ArrayList<RatedMessage> responses) {
        return responses.get(new Random().nextInt(responses.size() - 1));
    }

    protected RatedMessage send(RatedMessage topAnswer) {
        
       return topAnswer;
    }

     protected ArrayList<RatedMessage> calcAnswers(String[] messages, double baseQuality) {
         
         ArrayList<Message> allMessages = new ArrayList<>(); 
         for(String msg : messages){
             allMessages.add(new Message(msg));
         }
         
        return calcAnswers(allMessages, baseQuality);
    }
    
    protected ArrayList<RatedMessage> calcAnswers(ArrayList<Message> messages, double baseQuality) {
        ArrayList<RatedMessage> allAnswers = new ArrayList<>(); 
        for(Message answer : messages){
            double quality = modifiyQuality(answer, baseQuality);
            allAnswers.add(new RatedMessage(answer, quality));
        }
        return allAnswers;
    }
 
    private double modifiyQuality(Message msg, double baseQuality) {
        double quality = baseQuality;
        
        return quality;
    }

    protected RatedMessage getNullResponse(){
        return new RatedMessage("",0.0);
    }
    
    
    
}
