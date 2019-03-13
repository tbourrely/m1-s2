const Ice = require("ice").Ice;
const MP3Collection = require("./generated/MP3Collection").MP3Collection;
 
(async function()
{
    let communicator;
    try
    {
        communicator = Ice.initialize();
        const base = communicator.stringToProxy("MP3Collection:default -p 10000");
        const collection = await MP3Collection.CollectionPrx.checkedCast(base);
        if(collection)
        {

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
                        
                        const result = await collection.search('name', name);

                        if ('None' !== result) {
                            const jsonResult = JSON.parse(result);
                            const rTrack = new MP3Collection.Track(
                                jsonResult.artist,
                                jsonResult.name,
                                jsonResult.year,
                                jsonResult.file
                            );
                            await collection.remove(rTrack);
                        } else {
                            console.log('No such track');
                        }
                    }

                    if ('s' === line) {
                        process.stdout.write("Name : ");
                        const name = await getline();
                        
                        const result = await collection.search('name', name);

                        if ('None' !== result) {
                            console.log(result.artist);
                            console.log(result.name);
                            console.log(result.year);
                            console.log(result.file);
                        } else {
                            console.log('No such track');
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