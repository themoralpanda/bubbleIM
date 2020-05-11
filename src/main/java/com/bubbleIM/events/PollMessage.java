package com.bubbleIM.events;

import com.google.common.base.MoreObjects;

public class PollMessage extends SocketMessage {

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
    return MoreObjects.toStringHelper(this)
        .add("readFromTimestamp", readFromTimestamp)
        .add("numberOfMessages", numberOfMessages)
        .toString();
  }
}
