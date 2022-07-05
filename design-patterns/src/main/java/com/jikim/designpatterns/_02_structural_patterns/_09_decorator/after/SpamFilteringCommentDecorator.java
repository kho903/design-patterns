package com.jikim.designpatterns._02_structural_patterns._09_decorator.after;

public class SpamFilteringCommentDecorator extends CommentDecorator {
	public SpamFilteringCommentDecorator(CommentService commentService) {
		super(commentService);
	}

	@Override
	public void addComment(String comment) {
		if (!isSpam(comment))
			super.addComment(comment);
	}

	private boolean isSpam(String comment) {
		return comment.contains("http");
	}
}
