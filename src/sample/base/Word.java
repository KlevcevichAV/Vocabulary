package sample.base;

import java.util.ArrayList;

public class Word {
    private String wordInEn;
    private String wordInRu;
    private ArrayList<String> synonymForEn;
    private ArrayList<String> synonymForRu;

    public String copy(int begin, int end, String expression) {
        String result = "";
        StringBuilder stringBuilder = new StringBuilder(result);
        for (int i = begin; i < end; i++) {
            stringBuilder.append(expression.charAt(i));
        }
        result = stringBuilder.toString();
        return result;
    }

    private ArrayList<String> parsingSynonym(String synonym){
        ArrayList<String> result = new ArrayList<>();
        int begin = 0;
        for(int i = 0; i < synonym.length(); i++){
            if(synonym.charAt(i) == '\n' || i == synonym.length() - 1){
                i++;
                result.add(copy(begin, i, synonym));
                begin = i;
            }
        }
        return result;
    }

    public Word(String wordInEn, String wordInRu){
        this.wordInEn = wordInEn;
        this.wordInRu = wordInRu;
    }

    public Word(String wordInEn, String wordInRu, String synonymForEn, String synonymForRu){
        this.wordInEn = wordInEn;
        this.wordInRu = wordInRu;
        this.synonymForEn = parsingSynonym(synonymForEn);
        this.synonymForRu = parsingSynonym(synonymForRu);
    }

    public String getWordInEn() {
        return wordInEn;
    }

    public String getWordInRu() {
        return wordInRu;
    }

    public String getSynonymForEn() {
        StringBuilder result = new StringBuilder();
        for (String word: synonymForEn) {
            result.append(word);
        }
        return result.toString();
    }

    public String getSynonymForRu() {
        StringBuilder result = new StringBuilder();
        for (String word: synonymForRu) {
            result.append(word);
        }
        return result.toString();
    }
}
