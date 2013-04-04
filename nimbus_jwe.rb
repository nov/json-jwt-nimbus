module NimbusJWE
  module_function

  def decrypt(jwe, private_key_path)
    compile
    Dir.chdir(current_dir) do
      command = "java -classpath #{jar_file_paths.join(':')}:. JWEDecrypter_RSA #{jwe} #{private_key_path}"
      `#{command}`
    end
  end

  def compile
    unless @compiled
      Dir.chdir(current_dir) do
        command = "javac -classpath #{jar_file_paths.join(':')} JWEDecrypter_RSA.java"
        `#{command}`
      end
      @compiled = true
    end
  end

  def current_dir
    File.dirname(__FILE__)
  end

  def jar_file_paths
    Dir.glob './*.jar'
  end
end