package com.jikim.designpatterns._02_structural_patterns._09_decorator.before;

public class SpamFilteringCommentService extends CommentService {

	@Override
	public void addComment(String comment) {
		boolean isSpam = isSpam(comment);
		if (!isSpam) {
			super.addComment(comment);
		}
	}

	private boolean isSpam(String comment) {
		return comment.contains("http");
	}
}
