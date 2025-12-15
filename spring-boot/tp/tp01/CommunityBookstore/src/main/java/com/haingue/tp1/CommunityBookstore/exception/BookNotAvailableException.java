package com.haingue.tp1.CommunityBookstore.exception;

public class BookNotAvailableException extends BusinessException {

  public BookNotAvailableException(String isbn) {
    super("The book is not available: " + isbn);
  }

}
