package com.example.rest.example.entities;

/** 
 * This example entity is a simple POJO that can be
 * shared between server and clients.
 * @author <a href="mailto:dabecker@paypal.com">Dan Becker</a>
 */
public class Message {

    protected String content;

    public Message(String content) {
        this.setContent(content);
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		return equals((Message) obj );
	}

	public boolean equals(Message other) {
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Message [content=" + content + "]";
	}
}