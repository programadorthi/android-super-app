#!/bin/bash
# declare an array of 124 features plus original reach 125
declare -a arr=("aa" "ab" "ac" "ad" "ae" "af" "ag" "ah" "ai" "aj" "ak" "al" "am" "an" "ao" "ap" "aq" "ar" "as" "at" "au" "av" "aw" "ax" "ay" "az" "ba" "bb" "bc" "bd" "be" "bf" "bg" "bh" "bi" "bj" "bk" "bl" "bm" "bn" "bo" "bp" "bq" "br" "bs" "bt" "bu" "bv" "bw" "bx" "by" "bz" "ca" "cb" "cc" "cd" "ce" "cf" "cg" "ch" "ci" "cj" "ck" "cl" "cm" "cn" "co" "cp" "cq" "cr" "cs" "ct" "cu" "cv" "cw" "cx" "cy" "cz" "da" "db" "dc" "dd" "de" "df" "dg" "dh" "di" "dj" "dk" "dl" "dm" "dn" "do" "dp" "dq" "dr" "ds" "dt" "du" "dv" "dw" "dx" "dy" "dz" "ea" "eb" "ec" "ed" "ee" "ef" "eg" "eh" "ei" "ej" "ek" "el" "em" "en" "eo" "ep" "eq" "er" "es" "et")

# declare an array of submodules because 125 * 8 = 1k features + modules
declare -a arr2=("di" "domain" "domain-fake" "domain-impl" "domain-test" "ui" "ui-fake" "ui-test")

# define defaults directories
origindir="features/norris-facts"
maindir="$origindir/src/main/kotlin/dev/programadorthi/norris"
testdir="$origindir/src/test/kotlin/dev/programadorthi/norris"

# base package used in all modules
package="dev.programadorthi.norris"

# build.gradle.kts (app) line to add implementation after
line=25

for suffix in "${arr[@]}"
do
  newfolder="$origindir-$suffix"
  newpackage="$package.$suffix"

  # Create a full copy of original directory
  cp -r $origindir $newfolder

  for submodule in "${arr2[@]}"
  do
    newfoldermaindir="$newfolder/$submodule/$maindir"
    newfoldertestdir="$newfolder/$submodule/$testdir"

    if [ -d "$newfoldermaindir" ]; then
      rm -rf $newfoldermaindir
      mkdir -p "$newfoldermaindir/$suffix"
      cp -R "$origindir/$submodule/$maindir"/* "$newfoldermaindir/$suffix"
    elif [ -d "$newfoldertestdir" ]; then
      rm -rf $newfoldertestdir
      mkdir -p "$newfoldertestdir/$suffix"
      cp -R "$origindir/$submodule/$testdir"/* "$newfoldertestdir/$suffix"
    fi
  done

  # Find all *.kt or AndroidManifest.xml files and replace they package with new suffix
  find $newfolder -type f \( -iname \*.kt -o -name AndroidManifest.xml \) -exec sed -i "s/$package/$newpackage/g" {} \;
  
  # Find all Gradle Kotlin DSL files in the new module folder and replace they implementations with new suffix
  find $newfolder -type f -name "*.kts" -exec sed -i "s/NorrisFacts/NorrisFacts${suffix^}/g" {} \;
  
  # Add new module implementation to the app build gradle file
  sed -i "$line a implementation(project(LibraryModules.Features.NorrisFacts${suffix^}.UI))" app/build.gradle.kts
  
  # Increment line number to add next module implementation
  ((line=line+1))
done
