// CSc 127B Fall 2016 Section 9 Parts I and II

class LLNode<E> {

    public E data;
    public LLNode<E> next;

    public LLNode (E value)
    {
        data = value;
        next = null;
    }

    public LLNode (E value, LLNode<E> ref)
    {
        data = value;
        next = ref;
    }

    public E getData()
    {
       return data;
    }
    public LLNode<E> getNext()           
    {
        return next;
    }
    public void setData(E dataItem)
    {
        data = dataItem;
    }
    public void setNext(LLNode<E> nextItem)
    {
        next = nextItem;
    }

} // LLNode<E>