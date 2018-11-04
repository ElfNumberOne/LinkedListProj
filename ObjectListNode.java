public class ObjectListNode {
	 private Object info;
		private ObjectListNode next;
		/**
		*Constructor for objects of class ObjectListNode
		*/  
		public ObjectListNode() {
			info = null;
			next = null;
		}
		/**
		*one arg Constructor for objects of class ObjectListNode
		*@param o
		*/ 
		public ObjectListNode (Object o) {
			info = o;
			next = null;
		}    
		/**
		*two arg Constructor for objects of class ObjectListNode
		*@param o
		*@param p
		*/ 
		public ObjectListNode (Object o, ObjectListNode p) {
			info = o;
			next = p;
		}       
		/**
		*Sets info field
		*@param o
		*/
		public void setInfo(Object o) {
			info = o;
		}    
		/**
		*Returns object in info field
		*@return info
		*/
		public Object getInfo() {
			return info;
		}
		/**
		*Sets next field
		*@param p
		*/
		public void setNext(ObjectListNode p) {
			next = p;
		}
		/**
		*Returns object in info field
		*@return next
		*/
		public ObjectListNode getNext() {
			return next;
		}
}