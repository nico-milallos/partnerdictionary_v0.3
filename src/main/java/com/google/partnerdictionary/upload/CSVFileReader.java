package com.google.partnerdictionary.upload;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class CSVFileReader {

  private static final char DEFAULT_SEPARATOR = ',';
  private static final char DEFAULT_QUOTE = '"';

  private HashMap<String, String> TABLE_MAPPING =  new HashMap<String, String>();

  private String csvFileDirectory;

  public CSVFileReader(
      String csvFileDirectory) throws FileNotFoundException {
    this.csvFileDirectory = csvFileDirectory;
  }

  public List<List<String>> getData() throws FileNotFoundException {
    String csvFile = csvFileDirectory;
    Scanner scanner = new Scanner(new File(csvFile));
    List<List<String>> parsedLines = new ArrayList<>();
    List<String> line;

    boolean isFirst = true;
    while (scanner.hasNext()) {
      line = parseLine(scanner.nextLine());
      if(isFirst) {
        isFirst = false;
        continue;
      }
      parsedLines.add(line);
    }
    scanner.close();
    return parsedLines;
  }

  public static List<String> parseLine(String cvsLine) {
    return parseLine(cvsLine, DEFAULT_SEPARATOR, DEFAULT_QUOTE);
  }

  public static List<String> parseLine(String cvsLine, char separators) {
    return parseLine(cvsLine, separators, DEFAULT_QUOTE);
  }

  public static List<String> parseLine(String cvsLine, char separators, char customQuote) {

    List<String> result = new ArrayList<>();

    //if empty, return!
    if (cvsLine == null && cvsLine.isEmpty()) {
      return result;
    }

    if (customQuote == ' ') {
      customQuote = DEFAULT_QUOTE;
    }

    if (separators == ' ') {
      separators = DEFAULT_SEPARATOR;
    }

    StringBuffer curVal = new StringBuffer();
    boolean inQuotes = false;
    boolean startCollectChar = false;
    boolean doubleQuotesInColumn = false;

    char[] chars = cvsLine.toCharArray();

    for (char ch : chars) {

      if (inQuotes) {
        startCollectChar = true;
        if (ch == customQuote) {
          inQuotes = false;
          doubleQuotesInColumn = false;
        } else {

          //Fixed : allow "" in custom quote enclosed
          if (ch == '\"') {
            if (!doubleQuotesInColumn) {
              curVal.append(ch);
              doubleQuotesInColumn = true;
            }
          } else {
            curVal.append(ch);
          }

        }
      } else {
        if (ch == customQuote) {

          inQuotes = true;

          //Fixed : allow "" in empty quote enclosed
          if (chars[0] != '"' && customQuote == '\"') {
            curVal.append('"');
          }

          //double quotes in column will hit this!
          if (startCollectChar) {
            curVal.append('"');
          }

        } else if (ch == separators) {

          result.add(curVal.toString());

          curVal = new StringBuffer();
          startCollectChar = false;

        } else if (ch == '\r') {
          //ignore LF characters
          continue;
        } else if (ch == '\n') {
          //the end, break!
          break;
        } else {
          curVal.append(ch);
        }
      }

    }
    result.add(curVal.toString());

    return result;
  }

}