package stackoverflow;

public class CommentService {
    private static CommentService commentService;
    private CommentService() {

    }

    public static CommentService getCommentService() {
        if (commentService == null) {
            synchronized (CommentService.class) {
                if (commentService == null) {
                    commentService = new CommentService();
                }
            }
        }
        return commentService;
    }

    public Comment addComment(Member user, Commentable commentable,String content) {
        return user.addComment(commentable, content);
    }
}
