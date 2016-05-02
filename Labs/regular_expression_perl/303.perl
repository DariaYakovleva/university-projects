use List::MoreUtils qw/ uniq /;

$str = '';         
while($line = <STDIN>) {
	$str = $str.$line;
}

$tag = '<a[^>]*href *= *"([^"]*)"[^>]*>';
@links = $str =~ /$tag/g;
@sites = grep {s|^([^:]*://?)?([^@]*@)?([a-zA-z0-9][^:/]*\.\w+)(:\d+)?(/.*)?$|\3|} @links;
@sites = uniq @sites;
@sites = sort @sites;
print join "\n" => @sites;
