package project4;

import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * A class to represent a double linked list of nodes. The list is Iterable to indicate that we can iterate over the data in the list.
 */
public class DoubleLinkedList<T> implements Iterable<T> {
	/** a reference to the first node of the double linked list */
	private DLNode<T> front;

	/** a reference to the last node of a double linked list */
	private DLNode<T> back;

	/** Create an intially empty double linked list. */
	public DoubleLinkedList() {
		front = back = null;
	}

	/** 
	 * Returns true if the list is empty.
	 * @return  true if the list has no nodes
	 */
	public boolean isEmpty() {
		return (getFront() == null);
	}

	/**
	 * Returns the reference to the first node of the linked list.
	 * @return the first node of the linked list
	 */
	protected DLNode<T> getFront() {
		return front;
	}

	/**
	 * Sets the first node of the linked list.
	 * @param node  the node that will be the head of the linked list.
	 */
	protected void setFront(DLNode<T> node) {
		front = node;
	}

	/**
	 * Returns the reference to the last node of the linked list.
	 * @return the last node of the linked list
	 */
	protected DLNode<T> getBack() {
		return back;
	}

	/**
	 * Sets the last node of the linked list.
	 * @param node the node that will be the last node of the linked list
	 */
	protected void setBack(DLNode<T> node) {
		back = node;
	}

	/*----------------------------------------*/
	/* METHODS TO BE ADDED DURING LAB SESSION */
	/*----------------------------------------*/

	/**
	 * Add an element to the head of the linked list.
	 * @param element  the element to add to the front of the linked list
	 */
	public void addToFront(T element) {
		if (this.isEmpty()) {
			DLNode<T> newNode  = new DLNode<T>(element, null, null);
			front = newNode;
			back = newNode;
		} else {
			DLNode<T> frontNode = this.getFront();
			DLNode<T> newNode  = new DLNode<T>(element, null, frontNode);
			newNode.setNext(frontNode);
			front = newNode;
		}
	}

	/**
	 * Add an element to the tail of the linked list.
	 * @param element  the element to add to the tail of the linked list
	 */
	public void addToBack(T element) {
		DLNode<T> newNode  = new DLNode<T>(element, null, null);
		if (this.isEmpty()) {
			front = newNode;
			back = newNode;
		} else {
			DLNode<T> backNode = getBack();
			backNode.setNext(newNode);
			newNode.setPrevious(backNode);
			this.setBack(newNode);
		}

	}

	/**
	 * Remove and return the element at the front of the linked list.
	 * @return the element that was at the front of the linked list
	 * @throws NoSuchElementException if attempting to remove from an empty list
	 */
	public T removeFromFront() {
		if (!isEmpty()) {
			DLNode<T> frontNode = this.getFront();
			DLNode<T> nextNode = frontNode.getNext();
			if (nextNode != null) {
				nextNode.setPrevious(null);
			}
			this.setFront(nextNode);
			return frontNode.getElement();
		} else {
			throw new NoSuchElementException();
		}
	}

	/**
	 * Remove and return the element at the back of the linked list.
	 * @return the element that was at the back of the linked list
	 * @throws NoSuchElementException if attempting to remove from an empty list
	 */
	public T removeFromBack() {
		if (!isEmpty()) {
			DLNode<T> backNode = getBack();
			DLNode<T> prevNode = backNode.getPrevious();
			if (prevNode != null) {
				prevNode.setNext(null);
				this.setBack(prevNode);
			} else {
				this.setFront(null);
			}
			return backNode.getElement();
		} else {
			throw new NoSuchElementException();
		}
	}
	

	/**
	 * Returns an interator for the linked list that starts at the head of the list and runs to the tail.
	 * @return  the iterator that runs through the list from the head to the tail
	 */
	@Override
	public ListIterator<T> iterator() {
		return new DoubleLinkedListIterator();
	}

	/**
	 * Removes the passed node from the linked list
	 */
	private void removeNode(DLNode removeNode) {
		
		//If front node
		if (removeNode.getPrevious() == null) {
			front = removeNode.getNext();
			removeNode.getNext().setPrevious(null);
		} 
		//Else if last node
		else if (removeNode.getNext() == null) {
			back = removeNode.getPrevious();
			removeNode.getPrevious().setNext(null);
		}
		// Any middle nodes
		else {
			removeNode.getNext().setPrevious(removeNode.getPrevious());
			removeNode.getPrevious().setNext(removeNode.getNext());
		}
	}
	
	// assumes no calls to DoublyLinkedList.add() during iteration
	/**
	 * Inner private class to implement list iterator for the linked list
	 *
	 */
	private class DoubleLinkedListIterator<T> implements ListIterator<T> {
		private DLNode<T> currentDLNode;
		private DLNode<T> lastAccessedDLNode;
		private DLNode<T> previousDLNode;
		private DLNode<T> nextDLNode;
		
		private int index = 0;
		
		DoubleLinkedListIterator() {
			currentDLNode = (DLNode<T>) getFront();
		}

		
		/**
		  * Add an element to the linked list.
		  * @param element is added to the linked list and index is incremented 
		 */
		@Override				
		public void add(T element) {
			
			DLNode<T> newDLNode = new DLNode<T>(element, null, null);
			
			previousDLNode = currentDLNode.getPrevious();
			
			newDLNode.setPrevious(previousDLNode);
			previousDLNode.setNext(newDLNode);
			newDLNode.setNext(currentDLNode);
			currentDLNode.setPrevious(newDLNode);
			index++;
			lastAccessedDLNode = null;
		}
		
		/**
		   * Determines whether an next element is present in the list
		   * @return true if and only if the next element is in the list
		 */		
		@Override
		public boolean hasNext() {
			if (currentDLNode != null && currentDLNode.getNext() != null)
				return true;
			else
				return false;
		}
		
		/**
		   * Determines whether an previous element is present in the list
		   * @return true if and only if the previous element is in the list
		 */		
		@Override
		public boolean hasPrevious() {
			if (currentDLNode != null && currentDLNode.getPrevious() != null)
				return true;
			else
				return false;
		}
		
		/**
		   * Determines whether an next element is present if present returns the element from the list
		   * @return next element if and only if the next element is in the list
		 */		
		@Override
		public T next() {
			// check if next element is present
			if (!hasNext())
				throw new NoSuchElementException();
			
			lastAccessedDLNode = currentDLNode;
			currentDLNode = currentDLNode.getNext();
			index++;
			return lastAccessedDLNode.getElement();
		}
		
		/**
		   * Determines whether an previous element is present, if present returns the element from the list
		   * @return previous element if and only if the previous element is in the list
		 */		
		@Override
		public T previous() {
			// check if next element is present
			if (!hasPrevious())
				throw new NoSuchElementException();
			
			currentDLNode = currentDLNode.getPrevious();
			index--;
			lastAccessedDLNode = currentDLNode;
			return lastAccessedDLNode.getElement();		
		}
		
		
		/**
		 *removes an element from the linked list.
		 */
		@Override
		public void remove() {
			// check if lastAccessedDLNode is present
			if (lastAccessedDLNode == null)
				throw new IllegalStateException();
			
			if (currentDLNode == lastAccessedDLNode) {
				index--;
			}
			
			nextDLNode = lastAccessedDLNode.getNext();
			previousDLNode = lastAccessedDLNode.getPrevious();
			
			removeNode((DLNode<T>) lastAccessedDLNode);
			
			lastAccessedDLNode = null;
		}

		@Override
		public int nextIndex() {
			// Returns current index plus 1
			return index + 1;
		}

		@Override
		public int previousIndex() {
			// Returns current index minus 1
			return index - 1;
		}

		@Override
		public void set(T arg0) {
			// Method not supported
			throw new UnsupportedOperationException();
		}	
	}

}



