package com.bubbleIM.events;

public class PollEvent extends SocketEvent {

  private long readFromTimestamp;
  private int numberOfMessages;

  public long getReadFromTimestamp() {
    return readFromTimestamp;
  }

  public void setReadFromTimestamp(long readFromTimestamp) {
    this.readFromTimestamp = readFromTimestamp;
  }

  public int getNumberOfMessages() {
    return numberOfMessages;
  }

  public void setNumberOfMessages(int numberOfMessages) {
    this.numberOfMessages = numberOfMessages;
  }

  @Override
  public String toString() {
    return super.getToStringHelper()
        .add("readFromTimestamp", readFromTimestamp)
        .add("numberOfMessages", numberOfMessages)
        .toString();
  }
}
