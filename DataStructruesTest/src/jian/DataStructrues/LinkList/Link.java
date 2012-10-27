package jian.DataStructrues.LinkList;
/**
 * 链表节点
 * @author JOJO
 * @date 2012-10-22
 */
public class Link
{
    public Link next;
    public String data;
    
    public Link()
    {
    }
    
    public Link(Link next, String data)
    {
        super();
        this.next = next;
        this.data = data;
    }
    public Link getNext()
    {
        return next;
    }
    public void setNext(Link next)
    {
        this.next = next;
    }
    public String getData()
    {
        return data;
    }
    public void setData(String data)
    {
        this.data = data;
    }
    
}
