class Formatter{
  static String formatDate(List<int> dateList) {
    return dateList[2].toString() +
        "/" +
        dateList[1].toString() +
        "/" +
        dateList[0].toString() +
        " " +
        dateList[3].toString() +
        ":" +
        dateList[4].toString();
  }
}