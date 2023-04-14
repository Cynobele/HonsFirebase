package uk.ac.abertay.honsfirebase;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class QuestionCollection {


    //TODO - change map to be <q_number, question>
    //a map to hold the questions
    private Map<Integer, Question> QUESTIONS = new HashMap<>();

    //add the question type and object to the Map
    public void addQuestion(Integer tag, Question q){
        QUESTIONS.put(tag, q);
    }


    //if map is empty, returns True
    public boolean isMapEmpty(){
        return QUESTIONS.isEmpty();
    }

    //remove any values inside the map
    public void emptyMapContent(){
        QUESTIONS.clear();
    }


    public Map<Integer, Question> getMap(){
        return QUESTIONS;
    }

    public int mapSize(){
        return QUESTIONS.size();
    }

    public Integer getKey(Map<Integer, Question> map, Question search){
        for(Map.Entry entry : map.entrySet()){
            if(entry.getValue() == search){
                return (Integer)entry.getKey();
            }
        }
        return null;
    }

    //retrieve a random question from the map
    //num is the length of the question set
    public Question getRandomQuestion(Integer num){

        int random = new Random().nextInt(num);

        return QUESTIONS.get(random);
    }



}
