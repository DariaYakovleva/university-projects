$str = '';
while ($line = <STDIN>) {
	$str = $str.$line;
}
$spaces = ' +';
$str =~ s/$spaces/ /g;

$startend = '(^( *\n)+)|((\n *)+$)';
$str =~ s/$startend//g;

$emptyline = '\n( *\n)+';
$str =~ s/$emptyline/\n\n/g;

$mids1 = '(\n|^) ';
$str =~ s/$mids1/\1/g;

$mids2 = ' (\n|$)';
$str =~ s/$mids2/\1/g;

print "$str";