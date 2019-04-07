using System;
namespace Application
{
    public class Client
    {
        public static void Main(String[] args)
        {
            TextCasing service = new TextCasing();
            Console.WriteLine(service.InvertStringCase("Test"));
        }
    }
}
