class Linear {
    private Integer[] hashTable;
    private int tableSize;

    public Linear(int size) {
        this.tableSize = size;
        this.hashTable = new Integer[tableSize];
    }

    private int hashFunction(int key) {
        return key % tableSize;
    }

    public void insert(int key) {
        int index = hashFunction(key);

        while (hashTable[index] != null) {
            index = (index + 1) % tableSize;
        }

        hashTable[index] = key; 
    }

    public boolean search(int key) {
        int index = hashFunction(key);

        while (hashTable[index] != null) {
            if (hashTable[index].equals(key)) {
                return true; 
            }
            index = (index + 1) % tableSize; 
        }

        return false;
    }

    public void display() {
        for (int i = 0; i < tableSize; i++) {
            if (hashTable[i] != null) {
                System.out.println("Index " + i + ": " + hashTable[i]);
            } else {
                System.out.println("Index " + i + ": null");
            }
        }
    }
}
