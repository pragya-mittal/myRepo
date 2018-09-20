package arrays;

class A
{
    static int test=1;
    public static void main(String[] args)
    {
        System.out.println(test);
        System.out.println("Main Method Parent");
    }
}
class C extends A
{
    public static void main(String[] args)
    {
        System.out.println("Main Method Child");
    }
}
