class Linear {
    private String[] hashTable;
    private int tableSize;

    public Linear(int size) {
        this.tableSize = size;
        this.hashTable = new String[tableSize];
    }

    private int hashFunction(String key) {
        int hashValue = 0;
        for (char c : key.toCharArray()) {
            hashValue += (int) c; 
        }
        return hashValue % tableSize; 
    }

    public void insert(String key) {
        int index = hashFunction(key);

        while (hashTable[index] != null) {
            index = (index + 1) % tableSize; 
        }

        hashTable[index] = key; 
    }

    public boolean search(String key) {
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
