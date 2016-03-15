package iResearcher;

public class Message {
	private String sender;
	private String receiver;
	private String text;
	
	public Message(String text, String sender, String receiver){
		this.text = text;
		this.sender = sender;
		this.receiver = receiver;
	}
	
	public String getText(){ return this.text; }
	public String getSender(){ return this.sender; }
	public String getReceiver(){ return this.receiver; }
}
