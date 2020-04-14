import java.util.*;

public class WordFrequencyGame {

    public static final String SPACE_PATTERN = "\\s+";
    public static final String DELIMITER_OF_NEWLINE = "\n";
    public static final String DELIMITER_OF_SPACE = " ";
    public static final String CALCULATE_ERROR = "Calculate Error";

    public String getResult(String inputSentence) {

        try {
            List<WordInfo> wordInfoList = calculateWordFrequency(inputSentence);
            return formatWordFrequencyResult(wordInfoList);
        } catch (Exception exception) {
            return CALCULATE_ERROR;
        }
    }

    private List<WordInfo> calculateWordFrequency(String inputSentence) {
        //split the input string with 1 to n pieces of spaces
        List<String> wordList = Arrays.asList(inputSentence.split(SPACE_PATTERN));

        List<WordInfo> wordInfoList = new ArrayList<>();
        for (String word : wordList) {
            int wordCount = Collections.frequency(wordList, word);
            wordInfoList.add(new WordInfo(word, wordCount));
        }

        //get the map for the next step of sizing the same word
        Map<String, List<WordInfo>> map = getWordFrequencyMap(wordInfoList);

        List<WordInfo> list = new ArrayList<>();
        for (Map.Entry<String, List<WordInfo>> entry : map.entrySet()) {
            WordInfo wordInfo = new WordInfo(entry.getKey(), entry.getValue().size());
            list.add(wordInfo);
        }
        wordInfoList = list;

        wordInfoList.sort((firstWordInfo, secondWordInfo) -> secondWordInfo.getWordCount() - firstWordInfo.getWordCount());
        return wordInfoList;
    }

    private String formatWordFrequencyResult(List<WordInfo> wordInfoList) {
        StringJoiner joiner = new StringJoiner(DELIMITER_OF_NEWLINE);
        for (WordInfo wordInfo : wordInfoList) {
            String wordWithCount = wordInfo.getWord() + DELIMITER_OF_SPACE + wordInfo.getWordCount();
            joiner.add(wordWithCount);
        }
        return joiner.toString();
    }

    private Map<String, List<WordInfo>> getWordFrequencyMap(List<WordInfo> wordInfoList) {
        Map<String, List<WordInfo>> wordFrequencyMap = new HashMap<>();
        for (WordInfo wordInfo : wordInfoList) {
            if (!wordFrequencyMap.containsKey(wordInfo.getWord())) {
                ArrayList arrayList = new ArrayList<>();
                arrayList.add(wordInfo);
                wordFrequencyMap.put(wordInfo.getWord(), arrayList);
            } else {
                wordFrequencyMap.get(wordInfo.getWord()).add(wordInfo);
            }
        }
        return wordFrequencyMap;
    }
}
