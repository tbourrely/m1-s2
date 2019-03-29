const Ice = require("ice").Ice;
const MP3Collection = require("./generated/MP3Collection").MP3Collection;
 
(async function()
{
    let communicator;
    try
    {
        communicator = Ice.initialize();
        const base = communicator.stringToProxy("SimpleMP3Manager:default -p 10000");
        const collection = await MP3Collection.CollectionPrx.checkedCast(base);
        if(collection)
        {

            collection.add(new MP3Collection.Track('ABR', 'Test', '2009', 'test'));

            menu();
            let line = null;

            do {
                process.stdout.write("==> ");
                for(line of await getline()) {
                    
                    if ('a' === line) {
                        process.stdout.write("Artist : ");
                        const artist = await getline();
                        process.stdout.write("Name : ");
                        const name = await getline();
                        process.stdout.write("Year : ");
                        const year = await getline();    
                        process.stdout.write("File path : ");
                        const file = await getline();
                        
                        const nTrack = new MP3Collection.Track(artist, name, year, file);
                        await collection.add(nTrack);
                    }

                    if ('r' === line) {
                        process.stdout.write("Name : ");
                        const name = await getline();
                        try {
                            const result = await collection.search('name', name);
                            if (result)
                                await collection.remove(result);
                            else
                                console.log('No music to remove');
                        } catch(error) {
                            console.log(error);
                        }
                    }

                    if ('s' === line) {
                        process.stdout.write("Field to seach on : \n");
                        process.stdout.write("1) Artist\n2) Name\n3) Year\n");
                        const fieldChoice = await getline();

                        process.stdout.write("Value to search : ");
                        const value = await getline();

                        let field = null;

                        switch(fieldChoice) {
                            case '1':
                                field = 'artist';
                                break;
                            case '2':
                                field = 'name';
                                break;
                            case '3':
                                field = 'year';
                                break;
                        }

                        if (field) {
                            const result = await collection.search(field, value);
                            if (result) {
                                console.log(result.artist);
                                console.log(result.name);
                                console.log(result.year);
                                console.log(result.file);
                            } else {
                                console.log('No such track');
                            }
                        }
                    }
                    
                    if ('?' === line) {
                        menu();
                    }
                }
            } while(line !== 'x')
        }
        else
        {
            console.log("Invalid proxy");
        }
    }
    catch(ex)
    {
        console.log(ex.toString());
        process.exitCode = 1;
    }
    finally
    {
        if(communicator)
        {
            await communicator.destroy();
        }
    }
}());

function menu()
{
    process.stdout.write(
`usage:
   a: add a track
   r: remove a track
   s: search a track
   x: exit
   ?: help
`);
}

function getline()
{
    return new Promise(resolve =>
                       {
                           process.stdin.resume();
                           process.stdin.once("data", buffer =>
                                              {
                                                  process.stdin.pause();
                                                  resolve(buffer.toString("utf-8").trim());
                                              });
                       });
}