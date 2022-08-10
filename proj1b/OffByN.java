public class OffByN implements CharacterComparator{
    private int N;
    @Override
    public boolean equalChars(char x, char y){
        return (x-y==N) || (y-x==N);
    }

    public OffByN(int n){
        this.N=n;
    }
}
