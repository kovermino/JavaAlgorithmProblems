package chapter1;

public class TreeMapNode {
	
	private final static int LESS = 0;
	private final static int GREATER = 1;
	private Comparable itsKey;
	private Object itsValue;
	private TreeMapNode[] nodes = new TreeMapNode[2];
	
	public TreeMapNode(Comparable key, Object value) {
		itsKey = key;
		itsValue = value;
	}
	
	/**
	 * @param key	: 탐색할 키
	 * @return		: 탐색된 결과 값
	 */
	public Object find(Comparable key) {
		if(key.compareTo(itsKey)==0) {
			return itsValue;
		}
		return findSubNodeForKey(selectSubNode(key), key);
	}
	
	/**
	 * @param key	: 현재 노드의 자식노드 중 하나를 선택할 키
	 * @return		: 주어진 키값이 현재 노드의 키값보다 이전이면 0 이후면 1
	 */
	private int selectSubNode(Comparable key) {
		return key.compareTo(itsKey)<0 ? LESS : GREATER;
	}
	
	/**
	 * @param node	: 탐색할 자식노드의 인덱스
	 * @param key	: 탐색할 키
	 * @return		: 탐색된 결과 노드
	 */
	private Object findSubNodeForKey(int node, Comparable key) {
		return nodes[node] == null ? null : nodes[node].find(key);
	}
	
	/**
	 * @param key	: 추가될 노드의 키
	 * @param value	: 추가될 노드의 값
	 */
	public void add(Comparable key, Object value) {
		if(key.compareTo(itsKey)==0) {
			itsValue = value;
		}else {
			addSubNode(selectSubNode(key), key, value);
		}
	}
	
	/**
	 * @param node	: 추가될 노드의 자리를 가리키는 인덱스(왼쪽자식: 0, 오른쪽자식:1)
	 * @param key	: 추가될 노드의 키
	 * @param value	: 추가될 노드의 값
	 */
	private void addSubNode(int node, Comparable key, Object value) {
		if(nodes[node] == null) {
			nodes[node] = new TreeMapNode(key, value);
		}else {
			nodes[node].add(key, value);
		}
	}

}
