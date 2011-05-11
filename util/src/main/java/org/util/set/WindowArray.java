package org.util.set;

import java.util.Arrays;
import java.util.Iterator;

/**
 * <p>
 * This class provides a static size array composed of generic types
 * <code>T</code>. You can add any elements you want. When the capacity of
 * the array is exceeded, elements are add to the beginning of the array (The
 * first elements are removed. The class contains an internal pointer to the
 * next position to use to add elements.
 * </p>
 * 
 * <p>
 * The principal advantage of this structure is when the you want to keep the
 * last n values of a flow of data; to perform averages or to draw graphics.
 * </p>
 * 
 * @author yoann
 * 
 * @param <T>
 */
public class WindowArray<T> implements Iterable<T>
{

	private Object[] array;

	private int size = 100;

	private int pointer = 0;

	/**
	 * Constructor with a specified size.
	 * @param i The size of the array.
	 */
	public WindowArray( int i )
	{
		size = i;
		array = new Object[size];
		pointer = 0;
	}

	/**
	 * Return a reference to the internal array.
	 * @return
	 */
	@SuppressWarnings( "unchecked" )
	public T[] getArray()
	{
		return (T[]) array;
	}

	/**
	 * Returns the position of the pointer.
	 * @return
	 */
	public int getPointer()
	{
		return pointer;
	}

	/**
	 * Returns the size of the array
	 * @return
	 */
	public int getSize()
	{
		return size;
	}

	/**
	 * Should not be used. Defines the position of the internal pointer (without
	 * any control).
	 * @param pointer The index of the internal pointer
	 */
	public void setPointer( int pointer )
	{
		this.pointer = pointer;
	}

	/**
	 * Should not be used. Insert a value at a specified position. This is not
	 * the normal usage for this class.
	 * @see #add(Object)
	 * @param i The position .
	 * @param value The value.
	 */
	public void setArray( int i, T value )
	{
		array[i] = value;

	}

	/**
	 * Add an element to the container. If the array capacity is exceeded, new
	 * values are added to the beginning of the array (starting at index 0) .
	 * Old values are removed. For example, if the array size is 100 then when
	 * the 100 th value is inserted at position 99 and the 101 th value is
	 * inserted at position 0 in the data array.
	 * @param n The Value to add to the array.
	 */
	public void add( T n )
	{
		array[pointer] = n;
		pointer = ( pointer + 1 ) % size;

	}

	/**
	 * Return the i th value. Note that this is not the normal way to use this
	 * class.
	 * @param i the index of the return object in the internal data structure.
	 * @return The i th value in the data structure.
	 */
	@SuppressWarnings( "unchecked" )
	public T getData( int i )
	{
		return (T) array[i];

	}

	/**
	 * Return the last added value.
	 * @see #add(Object)
	 * @return
	 */
	@SuppressWarnings( "unchecked" )
	public T peek()
	{
		return (T) array[( pointer + size - 1 ) % size];
	}

	@Override
	public String toString()
	{
		return Arrays.deepToString( array );
	}

	/**
	 * Clears the content of the array (a new Object array is created)
	 */
	public void clear()
	{
		array = new Object[size];
		pointer = 0;
	}

	/**
	 * Reinitializes the array by first clearing it and then assigning the given
	 * value to each line of the array.
	 * @param o The value use to reinitialize the array.
	 */
	public void reinit( T o )
	{
		clear();
		for( int i = 0; i < size; i++ )
		{
			array[i] = o;
		}
	}

	/**
	 * Returns true if <code>o</code> is part of the array.
	 * @param o Does this object belong to the array ?
	 * @return True if <code>o</code> is part of the array.
	 */
	public boolean contains( T o )
	{
		boolean t = false;
		for( int i = 0; i < size; i++ )
		{
			if( o == array[i] )
			{
				i = size;
				t = true;
			}
		}
		return t;
	}

	public Iterator<T> iterator()
	{
		
		return new Iterator<T>(){
			int position=pointer;
			public boolean hasNext()
			{
				if (position != ( pointer + size - 1 ) % size)
					return true;
				return false;
			}

			public T next()
			{
				T o = getArray()[position];
				position = ( position + 1 ) % size;
				return o;
			}

			public void remove()
			{
				// No !!!
			}};
	}
}