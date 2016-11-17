package org.learn.data;

import javax.persistence.*;

@Entity
@Table(name="ansOption")
public class Option {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="option_id")
	private int id;
	
	@Column(name="option_des")
	private String description;
	
	@ManyToOne
    @JoinColumn(name="question_id")
	private Question question;
	
	public Option() {
	}
	
	public Option(String des) {
		this.description = des;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
}
