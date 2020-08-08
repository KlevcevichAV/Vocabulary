package sample.base;

import java.util.ArrayList;
import java.util.List;

public class Word {
    // for synonym that is not in the database
//    private String word;

//    public String getWord(){return word;}
    // for usual word
    private String wordInEn;
    private String wordInRu;
//    private ArrayList<Word> synonymForEn;
//    private ArrayList<Word> synonymForRu;

//    public boolean checkWord(){
//        return word == null;
//    }

    private Word searchWordInDatabase(String searchWord, List<Word> database){
        for (Word word: database) {
            if(searchWord.equals(word.getWordInEn()) ||
                    searchWord.equals(word.getWordInRu())){
                return word;
            }
        }
        return null;
    }

    public String copy(int begin, int end, String expression) {
        String result = "";
        StringBuilder stringBuilder = new StringBuilder(result);
        for (int i = begin; i < end; i++) {
            stringBuilder.append(expression.charAt(i));
        }
        result = stringBuilder.toString();
        return result;
    }

//    private ArrayList<Word> parsingSynonym(String synonym, List<Word> database){
//        ArrayList<Word> result = new ArrayList<>();
//        int begin = 0;
//        for(int i = 0; i < synonym.length(); i++){
//            if(synonym.charAt(i) == '\n' || synonym.charAt(i) == ',' || i == synonym.length() - 1){
//                int check = 0;
//                if(synonym.charAt(i) != ',') i++; else check = 1;
//                String word = copy(begin, i, synonym);
//                i += check;
//                Word synonymWord = searchWordInDatabase(word, database);
//                if(synonymWord != null){
//                    result.add(synonymWord);
//                }else result.add(new Word(word));
//                begin = i;
//            }
//        }
//        return result;
//    }

    public Word(String wordInEn, String wordInRu){
        this.wordInEn = wordInEn;
        this.wordInRu = wordInRu;
    }

//    public Word(String word){
//        this.word = word;
//    }

//    public Word(String wordInEn, String wordInRu, String synonymForEn, String synonymForRu, List<Word> database){
//        this.wordInEn = wordInEn;
//        this.wordInRu = wordInRu;
////        this.synonymForEn = parsingSynonym(synonymForEn, database);
////        this.synonymForRu = parsingSynonym(synonymForRu, database);
//    }

    public String getWordInEn() {
        return wordInEn;
    }

    public String getWordInRu() {
        return wordInRu;
    }

//    public String getSynonymForEn() {
//        StringBuilder result = new StringBuilder();
//        for (Word word: synonymForEn) {
//            result.append(word.checkWord() ? word.getWord() : word.getWordInEn());
//        }
//        return result.toString();
//    }

//    public String getSynonymForRu() {
//        StringBuilder result = new StringBuilder();
//        for (Word word: synonymForRu) {
//            result.append(word.checkWord() ? word.getWord() : word.getWordInRu());
//        }
//        return result.toString();
//    }

//    public void addSynonym(Word word){
//        for (Word enSynonym: synonymForEn) {
//            if(!enSynonym.checkWord()){
//                if(enSynonym.getWord().equals(word.getWordInEn())){
//                    enSynonym = word;
//                }
//            }
//        }
//        for (Word ruSynonym: synonymForRu) {
//            if(ruSynonym.checkWord()){
//                if(ruSynonym.equals(word.getWordInEn())){
//                    ruSynonym = word;
//                }
//            }
//        }
//    }
}
