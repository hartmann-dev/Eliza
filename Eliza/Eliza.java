package Eliza;

import Eliza.Message.IMessage;
import Eliza.Message.Message;
import Eliza.Message.RatedMessage;
import Eliza.Responder.IResponder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.TreeMap;

public class Eliza implements IBot {

    private final ArrayList<IResponder> responders;
    private final ArrayList<Message> history = new ArrayList<>();

    private static final String ELIZA_PROMPT = "> ";

    private boolean running = true;

    public Eliza(ArrayList<IResponder> responders) {
        this.responders = responders;
        this.responders.add(new GreetingResponder());
        this.responders.add(new LeavingResponder());
    }

    public Eliza(IResponder[] responders) {
        this(new ArrayList(Arrays.asList(responders)));
    }

    
    @Override
    public void stop() {
        running = false;
    }

    
    @Override
    public boolean isRunning() {
        return running;
    }

    @Override
    public String answer(String request) {
        Message message = new Message(request);
        addToHistory(message);

        Message answer = getMostSuitableResponse(message);

        return send(answer);
    }

    @Override
    public String hello() {
        Message answer = getMostSuitableResponse(new Message(""));
        return send(answer);
    }
 
    private String send(Message answer) {
        addToHistory(answer);
        return answer.toString();
    }

    private Message getMostSuitableResponse(IMessage request) {
        TreeMap<Double, RatedMessage> suitable = new TreeMap<>();

        for (IResponder responder : responders) {
            RatedMessage response = responder.getAnswer(request);
            suitable.put(response.getQuality(), response);
        }
        return suitable.lastEntry().getValue();
    }

    @Override
    public String prompt() {
        return ELIZA_PROMPT;
    }

    private void addToHistory(Message msg){
        history.add(msg);
    }
    
    @Override
    public ArrayList<Message> history() {
        return history;
    }

    private class GreetingResponder implements IResponder {

        private static final double RESPONSE_QUALITY = 1.0;

        private final Message[] GREETINGS = {
            new Message("Hallo. Wie geht es Ihnen?"),
            new Message("Guten Tag. Wie ist es Ihnen heute?")
        };

        @Override
        public RatedMessage getAnswer(IMessage msg) {
            double q = 0.0;
            if (history.isEmpty()) {
                q = RESPONSE_QUALITY;
            }
            Message answer = GREETINGS[new Random().nextInt(GREETINGS.length)];
            return new RatedMessage(answer, q);
        }
    }

    private class LeavingResponder implements IResponder {

        private static final double RESPONSE_QUALITY = 1.0;

        private final Message[] EXIT_KEYWORDS = {
            new Message("quit"),
            new Message("ende")

        };

        private final Message[] LEAVINGS = {
            new Message("Tschüss!"),
            new Message("Auf Bald")
        };

        @Override
        public RatedMessage getAnswer(IMessage msg) {
            double q = 0.0;
            for (IMessage exitKey : EXIT_KEYWORDS) {
                if (exitKey.equals(msg)) {
                    stop();
                    q = RESPONSE_QUALITY;
                }
            }
            Message answer = LEAVINGS[new Random().nextInt(LEAVINGS.length)];
            return new RatedMessage(answer, q);
        }
    }

}
