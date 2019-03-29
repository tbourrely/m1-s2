
$filename = __DIR__.'/../mfe_target/guest.txt';
$handle = fopen($filename, 'w') or die('cannot open the file');
fclose($handle);