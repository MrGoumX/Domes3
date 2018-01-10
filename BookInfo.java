private class BookInfo {
  int copies;
  int internationalSBN;

  BookInfo(int internationalSBN, int copies) {
    this.internationalSBN = internationalSBN;
    this.copies = copies;
  }

  void setISBN(int ISBN) {
    this.internationalSBN = ISBN;
  }

  void updateCopies(int copies) {
    this.copies = copies;
  }

  int getISBN() {
    return internationalSBN;
  }

  int getCopies() {
    return copies;
  }
}
