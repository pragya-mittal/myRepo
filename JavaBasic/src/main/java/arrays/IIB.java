package arrays;


class B
{
    B()
    {
        System.out.println("B-Constructor Called");
    }

//    {
//        System.out.println("B-IIB block");
//    }

}

// Child class
class IIB extends B
{
    IIB()
    {
//        super();
        System.out.println("A-Constructor Called");
    }
//    {
//        System.out.println("A-IIB block");
//    }

    // main function
    public static void main(String[] args)
    {
        IIB a = new IIB();

        int c=1;
        System.out.println(c);
    }
}

