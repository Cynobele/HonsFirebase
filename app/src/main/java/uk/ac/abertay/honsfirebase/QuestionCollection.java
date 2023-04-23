package uk.ac.abertay.honsfirebase;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class QuestionCollection {


    //a map to hold the questions
    private Map<Integer, Question> QUESTIONS = new HashMap<>();
    private Map<Integer, Question> VARS_QUESTIONS = new HashMap<>();
    private Map<Integer, Question> COND_QUESTIONS = new HashMap<>();
    private Map<Integer, Question> OPER_QUESTIONS = new HashMap<>();
    private Map<Integer, Question> LOOP_QUESTIONS = new HashMap<>();
    private Map<Integer, Question> FUNC_QUESTIONS = new HashMap<>();

    //add the question type and object to the Map
    public void addQuestion(Integer tag, Question q){
        QUESTIONS.put(tag, q);
    }

    public void addMixedQuestion(String tag, Question q){
        String[] tag_vals = tag.split("_");
        switch(tag_vals[0]){
            case "vars":
                VARS_QUESTIONS.put(Integer.parseInt(tag_vals[1]), q);
                break;
            case "oper":
                // +5 to tag val so oper keys are 6-11, func is 21-26 etc
                OPER_QUESTIONS.put(Integer.parseInt(tag_vals[1])+10, q);
                break;
            case "cond":
                COND_QUESTIONS.put(Integer.parseInt(tag_vals[1])+20, q);
                break;
            case "loop":
                LOOP_QUESTIONS.put(Integer.parseInt(tag_vals[1])+30, q);
                break;
            case "func":
                FUNC_QUESTIONS.put(Integer.parseInt(tag_vals[1])+40, q);
                break;
        }
    }

    public void combineMaps(){
        QUESTIONS.putAll(VARS_QUESTIONS);
        QUESTIONS.putAll(OPER_QUESTIONS);
        QUESTIONS.putAll(COND_QUESTIONS);
        QUESTIONS.putAll(LOOP_QUESTIONS);
        QUESTIONS.putAll(FUNC_QUESTIONS);
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
