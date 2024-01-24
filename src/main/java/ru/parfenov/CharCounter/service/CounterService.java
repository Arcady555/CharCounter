package ru.parfenov.CharCounter.service;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CounterService {
    public String getCount(String str) {
        Map<Character, Integer> charMap = convertStringToMap(str);
        LinkedHashMap<Character, Integer> charLinkedMap = sortMap(charMap);
        return convertMapToString(charLinkedMap);
    }

    private Map<Character, Integer> convertStringToMap(String str) {
        char[] charArray = str.toCharArray();
        Set<Character> charSet = new HashSet<>();
        Map<Character, Integer> charMap = new HashMap<>();
        for (char element : charArray) {
            if (element == ' ' || element == '\n' || element == '"') {
                continue;
            } else {
                if (charSet.add(element)) {
                    charMap.put(element, 1);
                } else {
                    int i = charMap.get(element);
                    charMap.replace(element, i + 1);
                }
            }
        }
        return charMap;
    }

    private LinkedHashMap<Character, Integer> sortMap(Map<Character, Integer> map) {
        List<Map.Entry<Character, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        LinkedHashMap<Character, Integer> charLinkedMap = new LinkedHashMap<>();
        for (Map.Entry<Character, Integer> entry : list) {
            charLinkedMap.put(entry.getKey(), entry.getValue());
        }
        return charLinkedMap;
    }

    private String convertMapToString(Map<Character, Integer> map) {
        String rsl = "An empty string was passed!";
        StringBuilder stringBuilder = new StringBuilder();
        for (char element : map.keySet()) {
            stringBuilder.append("\"").append(element).append("\": ").append(map.get(element)).append(", ");
        }
        if (stringBuilder.length() != 0) {
            rsl = stringBuilder.deleteCharAt(stringBuilder.length() - 2).toString().trim();
        }
        return rsl;
    }
}