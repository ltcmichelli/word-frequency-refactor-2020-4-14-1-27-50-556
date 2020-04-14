import java.util.*;
import java.util.stream.Collectors;

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
        List<String> stringList = Arrays.asList(inputSentence.split(SPACE_PATTERN));

        List<WordInfo> wordInfoList = new ArrayList<>();

        stringList.stream().distinct().forEach(string -> {
            int wordCount = Collections.frequency(stringList, string);
            wordInfoList.add(new WordInfo(string, wordCount));
        });

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
}
