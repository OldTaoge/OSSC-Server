package space.oldtaoge.ossc.server.commons;


public class CodeStatus {
    /**
     * 请求成功。成功的含义取决于HTTP方法：
     *   GET：资源已被提取并在消息正文中传输。
     *   HEAD：实体标头位于消息正文中。
     *   POST：描述动作结果的资源在消息体中传输。
     *   TRACE：消息正文包含服务器收到的请求消息
     * */
    public static final int OK = 200;

    /**
     * 1.语义有误，当前请求无法被服务器理解。除非进行修改，否则客户端不应该重复提交这个请求。
     * 2.请求参数有误。
     */
    public static final int BadRequest = 400;

}
