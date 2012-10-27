package jian.DataStructrues.LinkList;

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        Link link = GenerateLink();
        traverseList(link);
        System.out.println("");
        Link reverse = ReverseLink(link);
        traverseList(reverse);
        
        
    }
    /**
     * 7.单链表交换任意两个元素（不包括表头）
                先一次遍历找到这两个元素curr1和curr2，同时存储这两个元素的前驱元素pre1和pre2。
                然后大换血
                注意特例，如果相同元素，就没有必要交换；如果有一个是表头，就不交换。
     * @throws Exception 
     */
    
    public static Link SwitchPoints(Link head, Link p, Link q) throws Exception
    {
        if (p == head || q == head)
            throw new Exception("No exchange with head");
        if (p == q)
            return head;
        //find p and q in the link
        Link curr = head;
        Link curr1 = p;
        Link curr2 = q;
        Link pre1 = null;
        Link pre2 = null;

        int count = 0;
    while (curr != null)
        {
            if (curr.next == p)
            {
                pre1 = curr;
                count++;
                if (count == 2)
                    break;
            }
            else if (curr.next == q)
            {
                pre2 = curr;
                count++;
                if (count == 2)
                    break;
            }
            curr = curr.next;
        }
        curr = curr1.next;
        pre1.next = curr2;
        curr1.next = curr2.next;
        pre2.next = curr1;
        curr2.next = curr;
        return head;
    }
    

    /**
     * 5.两个不交叉的有序链表的合并
     * 有两个有序链表，各自内部是有序的，但是两个链表之间是无序的。
            算法思路：当然是循环逐项比较两个链表了，如果一个到了头，就不比较了，直接加上去。
            注意，对于2个元素的Data相等（仅仅是Data相等哦，而不是相同的引用），我们可以把它视作前面的Data大于后面的Data，从而节省了算法逻辑。
     */
        
    static Link MergeTwoLink(Link head1, Link head2)
    {
        Link head = new Link(null, null);
        Link pre = head;
        Link curr = head.next;
        Link curr1 = head1;
        Link curr2 = head2;
        //两组比较,直到其中一组到了尽头
        while (curr1.next != null && curr2.next != null)
        {
            if (Integer.parseInt(curr1.next.data) < Integer.parseInt(curr2.next.data))
            {
                curr = new Link(null, curr1.next.data);
                curr1 = curr1.next;
            }
            else
            {
                curr = new Link(null, curr2.next.data);
                curr2 = curr2.next;
            }
            pre.next = curr;
            pre = pre.next;
        }
        //如果head1还有
        while (curr1.next != null)
        {
            curr = new Link(null, curr1.next.data);
            curr1 = curr1.next;
            pre.next = curr;
            pre = pre.next;
        }
        //如果head2还有
        while (curr2.next != null)
        {
            curr = new Link(null, curr2.next.data);
            curr2 = curr2.next;
            pre.next = curr;
            pre = pre.next;
        }
        return head;
    }

    /**
     * 3.找出单链表的中间元素
     * 算法思想：类似于上题，还是使用两个指针first和second，只是first每次走一步，second每次走两步：
     */
    
    static List<Link> GetMiddleOne(Link head)
    {
        List<Link> results = new ArrayList<Link>();
        Link first = head;
        Link second = head;
        while (first != null && first.next != null)
        {
            first = first.next.next;
            second = second.next;
        }
        if(first!=null) results.add(second.next);//说明链表长度为偶数,要返回中间的两个
            
        results.add(second);
        return results;
    }

    /**
     * 找出单链表的倒数第4个元素
     * 思路:建立两个指针，第一个先走4步，然后第2个指针也开始走，两个指针步伐（前进速度）一致。
     * @param head
     * @return
     * @throws Exception 
     */
    static Link GetLast4thOne(Link head) throws Exception
    {
        Link first = head;
        Link second = head;
        for (int i = 0; i < 4; i++)
        {
            if (first.next == null)
                throw new Exception("Less than 4 elements");
            first = first.next;
        }
        while (first != null)
        {
            first = first.next;
            second = second.next;
        }
        return second;
    }

    
    /**
     * 反转链表
     * @param link
     * @return
     */
    protected static Link ReverseLink(Link link)
    {
        Link curr = link.next;
        Link nextnext = null;
        Link next = null;
        while(curr.next!=null)
        {
            next = curr.next;
            nextnext = next.next;
            next.next = link.next;
            link.next = next;
            curr.next = nextnext;
        }
        
        return link;
    }
    /**
     * 笨方法创建一个简单链表
     * @return
     */
    protected static Link GenerateLink()
    {
        Link node5 = new Link(null, "5");
        Link node4 = new Link(node5, "9");
        Link node3 = new Link(node4, "7");
        Link node2 = new Link(node3, "3");
        Link node1 = new Link(node2, "1");
        Link head = new Link(node1, null);
        return head;
        
    }
    /**
     * 遍历打印一个链表
     * @param head
     */
    protected static void traverseList(Link head)
    {
        Link curr = head;
        while (curr != null)
        {
            if(curr.getData()!=null)
                System.out.print(curr.getData()+"-> ");
            curr = curr.getNext();
        }

    }
    
}
