class FileFilter implements FilenameFilter {
    public boolean accept(File f, String filename) {
        return filename.endsWith("txt")
    }
}

new File("target/failsafe-reports").listFiles(new FileFilter()).each() { file->
    file.eachLine{line->
        if (line.endsWith(" <<< FAILURE!")) {
            throw new Exception("Integration test failure: " + file.getName());
        }
    }
}

return true;