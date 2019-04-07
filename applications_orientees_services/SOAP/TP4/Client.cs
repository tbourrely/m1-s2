using System;
namespace Application
{
    public class Client
    {
        public static void Main(String[] args)
        {
            HelloWorldService IBonjour = new HelloWorldService();
            Console.WriteLine(IBonjour.helloWorld("Thomas"));
        }
    }
}