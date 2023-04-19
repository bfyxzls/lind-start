package com.lind.chatgpt;

import com.unfbx.chatgpt.entity.chat.ChatChoice;
import com.unfbx.chatgpt.entity.common.Choice;
import org.junit.Test;

/**
 * @author lind
 * @date 2023/4/19 14:06
 * @since 1.0.0
 */
public class QuestionTest {

	@Test
	public void test() {
		String question = "我想用java写一个二叉树遍历";
		for (Choice choice : GptUtil.getAnswer(question)) {
			System.out.println(choice.getText());
		}
	}

	@Test
	public void chat() {
		String question = "hello";
		for (ChatChoice choice : GptUtil.getChat(question)) {
			System.out.println(choice.getMessage());
		}
	}
}
