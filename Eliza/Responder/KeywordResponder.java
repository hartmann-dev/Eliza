package Eliza.Responder;

import Eliza.Message.IMessage;
import Eliza.Message.RatedMessage;
import java.util.ArrayList;
import java.util.HashMap;

public class KeywordResponder extends Responder {

    private static final double BASE_QUALITY = 0.5;
    private static final double BONUS = 0.1;
    private HashMap<String, String[]> messages = new HashMap<>();

    public KeywordResponder(HashMap<String, String[]> msgs) {
        messages = msgs;
    }

    @Override
    public RatedMessage getAnswer(IMessage msg) {
        ArrayList<RatedMessage> allAnswers = new ArrayList<>();
        for (String word : msg.getWords()) {
            if (checkContain(word)) {
                allAnswers.addAll(calcAnswers(messages.get(word), BASE_QUALITY));
            }
        }
        if(allAnswers.isEmpty()){
            return send(getNullResponse());
        } else {
            RatedMessage topAnswer = getTopAnswer(modifyKeywordQuality(allAnswers));
            return send(topAnswer);            
        }
    }
    
    private ArrayList<RatedMessage> modifyKeywordQuality(ArrayList<RatedMessage> responses ){
        ArrayList<RatedMessage> modifiedResponses = new ArrayList<>();
        for(RatedMessage msg : responses){
            modifiedResponses.add(modifyKeywordQuality(msg));
        }
        return modifiedResponses;
    }
    
    private RatedMessage modifyKeywordQuality(RatedMessage response ){
        RatedMessage modifiedResponse;
        String hardCodedExample = "java";
        if(response.wordExists(hardCodedExample)){
           modifiedResponse = new RatedMessage(
                   response.getMessage(), response.getQuality() + BONUS
            );
        } else {
            modifiedResponse = response;
        }
        return modifiedResponse;
    }

    private boolean checkContain(String word) {
        return messages.get(word) != null;
    }

}
