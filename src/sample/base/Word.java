package sample.base;

import java.util.List;

public class Word {
    private String wordInEn;
    private String wordInRu;

    private Word searchWordInDatabase(String searchWord, List<Word> database) {
        for (Word word : database) {
            if (searchWord.equals(word.getWordInEn()) ||
                    searchWord.equals(word.getWordInRu())) {
                return word;
            }
        }
        return null;
    }

    private String copy(int begin, int end, String expression) {
        String result = "";
        StringBuilder stringBuilder = new StringBuilder(result);
        for (int i = begin; i < end; i++) {
            stringBuilder.append(expression.charAt(i));
        }
        result = stringBuilder.toString();
        return result;
    }

    public Word(String wordInEn, String wordInRu) {
        this.wordInEn = wordInEn;
        this.wordInRu = wordInRu;
    }

    public String getWordInEn() {
        return wordInEn;
    }

    public String getWordInRu() {
        return wordInRu;
    }
}
