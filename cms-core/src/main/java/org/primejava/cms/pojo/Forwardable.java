package org.primejava.cms.pojo;



public abstract interface Forwardable<T extends AbstractFowardPojo>
{
  public abstract void send(T paramT);
}
