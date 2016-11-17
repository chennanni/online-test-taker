package org.learn.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="question")
public class Question {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="question_id")
	private int id;
	
	@Column(name="question_des")
	private String description;
	
	@Column(name="question_ans")
	private String answer;
	
	@Column(name="question_exp")
	private String answerExplanation;
	
	@Column(name="next_id")
	private int nextQuestionId;
	
	@OneToMany(mappedBy="question")
	private List<Option> options = new ArrayList<Option>();
	
	@ManyToMany(mappedBy="questions")
	private List<Test> tests = new ArrayList<Test>();
	
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
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getAnswerExplanation() {
		return answerExplanation;
	}
	public void setAnswerExplanation(String answerExplanation) {
		this.answerExplanation = answerExplanation;
	}
	public List<Option> getOptions() {
		return options;
	}
	public void setOptions(List<Option> options) {
		this.options = options;
	}
	public List<Test> getTests() {
		return tests;
	}
	public void setTests(List<Test> tests) {
		this.tests = tests;
	}
	public int getNextQuestionId() {
		return nextQuestionId;
	}
	public void setNextQuestionId(int nextQuestionId) {
		this.nextQuestionId = nextQuestionId;
	}
}
