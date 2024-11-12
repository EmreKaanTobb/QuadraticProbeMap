
public class QuadraticProbeMap<K extends Integer , V>{  

    private mapEntry<K,V> DEFUNCT = new mapEntry<>(null, null);

    public mapEntry<K,V>[] hashTable; 

    protected int capacity;

    public int tableSize;

    static final double LOADFACTOR = 0.4;

    public QuadraticProbeMap(int size){

            hashTable = new mapEntry[size];
            tableSize = 0;
            capacity = size;
        
    }

    public int newSize(int size){

        int newSize = size*2;
        
        while (true) {
            boolean isPrime = true;
            for (int i = 2; i < newSize; i++) {
                if (newSize % i == 0) {
                    isPrime = false;
                    break;
                }
            }
            
            if (isPrime) {
                break;
            } else {
                newSize++;
            }
        }
    
        return newSize;
    }

    int hashValue(K key){
        return ((102 * key + 40790475) % 109345121);
    }

    public boolean isAvailable(int h){
        return(hashTable[h] == null || hashTable[h] == DEFUNCT );
    }

    public int doesContain(K key) {
        int index = hashValue(key) % capacity;
        int power = 1;
    
        while (hashTable[index] != null) {
            if (hashTable[index] == DEFUNCT) {

            } else if (hashTable[index].getKey().equals(key)) {
                return index; 
            }
    
            index = (hashValue(key) + power * power) % capacity;
            power++;
        }
    
        return -1; 
    }

    public int findPlace(K key) {
        
        int hash = hashValue(key);  
        int index = hash % capacity;
        int power = 1;
    
        while (!isAvailable(index)) {
            index = (hash + power * power) % capacity;
            power++;
        }
    
        return index;
    }

    V get(K key){

        int index = doesContain(key);

        if(index < 0){
            return null;
        }

        return hashTable[index].getValue();


    }

    V delete(K key){

        int index = doesContain(key);

        if(index < 0){
            return null;
        }

        V temp = hashTable[index].getValue();

        hashTable[index] = DEFUNCT;

        tableSize--;

        return temp;

    }

    void put(K key,V value){

        int index = doesContain(key);

        if(index >= 0){
            hashTable[index].setValue(value);
            return;
        }

        index = findPlace(key);

        hashTable[index] = new mapEntry<>(key, value);

        tableSize++;

        if((double)tableSize/capacity >= LOADFACTOR){
            resize(newSize(capacity));
        }

    }

    private void resize(int newSize) {
        mapEntry<K, V>[] oldTable = hashTable;
        hashTable = new mapEntry[newSize];
        capacity = newSize;
        tableSize = 0;
    
        for (mapEntry<K, V> entry : oldTable) {
            if (entry != null && entry != DEFUNCT) {
                put(entry.getKey(), entry.getValue());
            }
        }
    }

    public class mapEntry<K extends Integer , V>{ 
                                                
        K key;
        V value;
    
        public mapEntry(K key, V value){
            this.key = key;
            this.value = value; }
    
        public K getKey(){
            return key;
        }
    
        public V getValue(){
            return value;
        }
    
        public void setKey(K newKey){
            key = newKey;
        }
    
        public void setValue(V newValue){
            value = newValue;
        }
    
        
    
    }


}